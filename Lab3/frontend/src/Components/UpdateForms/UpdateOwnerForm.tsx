import { useState } from "react";
import { IOwner } from "../SearchForms/GetOwnerForm";
import { UpdateOwnerDTO } from "../../Api/Dto/UpdateOwnerDTO";
import { updateOwner } from "../../Api/Api";
import { OwnerTable } from "../Tables/OwnerTable";
import { Loader } from "../Loaders/Loader";

export function UpdateOwnerForm() {

	const [id, setId] = useState(0)
	const [name, setName] = useState('')
	const [date, setDate] = useState('')
	const [submited, setSubmited] = useState(false)
	const [owner, setOwner] = useState<IOwner>({ id: 0, name: '', birthDate: '' })
	const [isLoading, setLoading] = useState(false)

	const sendOwnerData = async () => {
		try {
			setLoading(true)
			const owner: UpdateOwnerDTO = { id: id, name: name, birthDate: date }
			const { data } = await updateOwner(owner)
			setOwner(data)
		}
		catch (error) {
			console.log("error during sending data " + error);
		}
		finally {
			setLoading(false)
		}
	}

	const submitHandler = async (event: React.FormEvent) => {
		event.preventDefault();
		setSubmited(!submited)
		await sendOwnerData()
	}

	const idChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setId(event.target.valueAsNumber)
	}

	const nameChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setName(event.target.value)
	}

	const dateChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setDate(event.target.value)
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
							placeholder="enter i"
							value={id}
							onChange={idChangeHandler}
						/>

						<input
							type="text"
							className="input-styles"
							placeholder="enter updated name"
							value={name}
							onChange={nameChangeHandler}
						/>

						<input
							type="date"
							className="other-input-styles"
							placeholder="enter updated birthdate, yyyy-mm-dd"
							value={date}
							onChange={dateChangeHandler}
						/>
						<button type="submit" className="submit-button">submit</button>

					</div>
				</form>}

			{!isLoading && submited && <OwnerTable users={[owner]} />}
		</>
	)
}