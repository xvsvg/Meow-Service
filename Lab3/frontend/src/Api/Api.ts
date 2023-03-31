import axios from "axios";
import { IOwner } from "../Components/SearchForms/GetOwnerForm";
import { ICat } from "../Components/SearchForms/GetCatForm";
import { CreateCatDTO } from "./Dto/CreateCatDTO";
import { CreateOwnerDTO } from "./Dto/CreateOwnerDTO";
import { UpdateCatDTO } from "./Dto/UpdateCatDTO";
import { UpdateOwnerDTO } from "./Dto/UpdateOwnerDTO";
import { OwnerPage } from "../Components/SearchForms/GetAllOwnersForm";
import { CatPage } from "../Components/SearchForms/GetAllCatsForm";

export const api = axios.create({ baseURL: process.env.REACT_APP_URL })

export const getOwner = async (id: number) => {
	if (process.env.NODE_ENV === "development")
		return await api.get<IOwner>("/owners/" + id)
	else return await api.get<IOwner>("api/owners/" + id)
}
export const getCat = async (id: number) => {
	if (process.env.NODE_ENV === "development")
		return await api.get<ICat>("/cats/" + id)
	else return await api.get<ICat>("api/cats/" + id)
}

export const getAllOwners = async () => {
	if (process.env.NODE_ENV === "development")
		return await api.get<OwnerPage>("/owners")
	else return await api.get<OwnerPage>("api/owners")
}

export const getAllCats = async () => {
	if (process.env.NODE_ENV === "development")
		return await api.get<CatPage>("/cats")
	else return await api.get<CatPage>("api/cats")
}

export const createCat = async (dto: CreateCatDTO) => {
	if (process.env.NODE_ENV === "development")
		return await api.post<ICat>("/cats/create", dto)
	else return await api.post<ICat>("api/cats/create", dto)
}

export const createOwner = async (dto: CreateOwnerDTO) => {
	if (process.env.NODE_ENV === "development")
		return await api.post<IOwner>("/owners/create", dto)
	else return await api.post<IOwner>("api/owners/create", dto)
}

export const updateCat = async (dto: UpdateCatDTO) => {
	if (process.env.NODE_ENV === "development")
		return await api.put<ICat>("/cats/update", dto)
	else return await api.put<ICat>("api/cats/update", dto)
}

export const updateOwner = async (dto: UpdateOwnerDTO) => {
	if (process.env.NODE_ENV === "development")
		return await api.put<IOwner>("/owners/update", dto)
	else return await api.put<IOwner>("api/owners/update", dto)
}

export const deleteCat = async (id: number) => {
	if (process.env.NODE_ENV === "development")
		return await api.delete<ICat>("/cats/delete/" + id)
	else return await api.delete<ICat>("api/cats/delete/" + id)
}


export const deleteOwner = async (id: number) => {
	if (process.env.NODE_ENV === "development")
		return await api.delete<IOwner>("/owners/delete/" + id)
	else return await api.delete<IOwner>("api/owners/delete/" + id)
}