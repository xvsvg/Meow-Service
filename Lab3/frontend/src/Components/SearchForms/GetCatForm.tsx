import { getCat } from "../../Api/Api";
import { useState } from "react";
import { CatTable } from "../Tables/CatTable";
import { IOwner } from "./GetOwnerForm";

export interface ICat {
	id: number,
	name: string,
	birthDate: string,
	color: string,
	breed: string,
	owner: IOwner
}

export function GetCatForm() {

	const [cats, setCats] = useState<ICat[]>([])
	const [entered, setEntered] = useState(false)
	const [id, setId] = useState(1)

	const fetchCat = async () => {
		try {
			const { data } = await getCat(id);
			setCats([data])
		}
		catch (error) {
			console.log("error fetching cats " + error)
		}
	}

	const submitHandler = async (event: React.FormEvent<HTMLFormElement>) => {
		event.preventDefault()
		setEntered(!entered)
		await fetchCat()
	}

	const idChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setId(event.target.valueAsNumber)
	}

	return (
		<>

			{!entered && <form onSubmit={submitHandler}>
				<div className="submit-form-container">

					<input
						type="number"
						className="input-styles"
						placeholder="enter id"
						value={id}
						onChange={idChangeHandler}
					/>

					<button type="submit" className="submit-button">search</button>
				</div>

			</form>}

			{entered && <CatTable cats={cats} />}
		</>
	)
}