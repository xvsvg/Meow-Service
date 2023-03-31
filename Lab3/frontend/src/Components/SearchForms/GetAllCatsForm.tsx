import { useEffect, useState } from "react";
import { ICat } from "./GetCatForm";
import { getAllCats } from "../../Api/Api";
import { CatTable } from "../Tables/CatTable";

export interface CatPage {
	currentPage: number,
	totalElements: number,
	totalPages: number,
	data: ICat[]
}

export function GetAllCatsForm() {

	const [cats, setCats] = useState<ICat[]>([])

	const fetchCats = async () => {
		try {
			const { data: catsData } = await getAllCats();
			setCats(catsData.data)
		}
		catch (error) {
			console.log("error fetching users " + error)
		}
	}

	useEffect(() => {
		(async () => await fetchCats())()
	}, [])

	return (
		<>
			<form>
				<div className="submit-form-container">
					<CatTable cats={cats} />
				</div>

			</form>
		</>
	);

}