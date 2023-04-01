import { useState } from "react";
import { getOwner } from "../../Api/Api";
import { OwnerTable } from "../Tables/OwnerTable";
import { Loader } from "../Loaders/Loader";

export interface IOwner {
	id: number;
	name: string;
	birthDate: string
}

export function GetOwnerForm() {

	const [users, setUsers] = useState<IOwner[]>([])
	const [entered, setEntered] = useState(false)
	const [id, setId] = useState(1)
	const [isLoading, setLoading] = useState(false)

	const fetchOwner = async () => {
		try {
			setLoading(true)
			const { data } = await getOwner(id);
			setUsers([data])
		}
		catch (error) {
			console.log("error fetching users " + error)
		}
		finally {
			setLoading(false)
		}
	}

	const submitHandler = async (event: React.FormEvent<HTMLFormElement>) => {
		event.preventDefault()
		setEntered(!entered)
		await fetchOwner()
	}

	const idChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setId(event.target.valueAsNumber)
	}

	return (
		<>
			{isLoading && <Loader />}

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

			{!isLoading && entered && <OwnerTable users={users} />}
		</>
	);
}