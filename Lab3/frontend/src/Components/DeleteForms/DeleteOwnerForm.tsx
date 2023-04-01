import { useState } from "react";
import { IOwner } from "../SearchForms/GetOwnerForm";
import { deleteOwner } from "../../Api/Api";
import { OwnerTable } from "../Tables/OwnerTable";
import { Loader } from "../Loaders/Loader";

export function DeleteOwnerForm() {

	const [id, setId] = useState(0)
	const [submited, setSubmited] = useState(false)
	const [owner, setOwner] = useState<IOwner>({ id: 0, name: '', birthDate: '' })
	const [isLoading, setLoading] = useState(false)

	const sendOwnerData = async () => {
		try {
			setLoading(true)
			const { data } = await deleteOwner(id)
			setOwner(data)
		}
		catch (error) {
			console.log("error during sending data" + error);
		}
		finally {
			setLoading(false)
		}
	}

	const submitHandler = async (event: React.FormEvent) => {
		event.preventDefault()
		setSubmited(!submited)
		await sendOwnerData()
	}

	const idChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setId(event.target.valueAsNumber)
	}

	return (
		<>
			{isLoading && <Loader />}

			{!submited &&
				<form onSubmit={submitHandler}>
					<div className="submit-form-container">

						<input
							type="number"
							className="input-styles"
							placeholder="enter id"
							value={id}
							onChange={idChangeHandler}
						/>
					</div>
					<button type="submit" className="submit-button">submit</button>

				</form>}

			{!isLoading && submited && <OwnerTable users={[owner]} />}
		</>
	)
}