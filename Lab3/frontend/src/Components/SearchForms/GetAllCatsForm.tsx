import { useEffect, useState } from "react";
import { ICat } from "./GetCatForm";
import { getAllCats } from "../../Api/Api";
import { CatTable } from "../Tables/CatTable";
import { Loader } from "../Loaders/Loader";

export interface CatPage {
	currentPage: number,
	totalElements: number,
	totalPages: number,
	data: ICat[]
}

export function GetAllCatsForm() {

	const [cats, setCats] = useState<ICat[]>([])
	const [isLoading, setLoading] = useState(false)

	const fetchCats = async () => {
		try {
			setLoading(true)
			const { data: catsData } = await getAllCats();
			setCats(catsData.data)
		}
		catch (error) {
			console.log("error fetching users " + error)
		}
		finally {
			setLoading(false)
		}
	}

	useEffect(() => {
		(async () => await fetchCats())()
	}, [])

	return (
		<>
			{isLoading && <Loader />}

			{!isLoading && <form>
				<div className="submit-form-container">
					<CatTable cats={cats} />
				</div>
			</form>}
		</>
	);

}