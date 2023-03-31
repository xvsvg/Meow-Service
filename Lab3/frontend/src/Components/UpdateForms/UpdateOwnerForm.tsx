import { useState } from "react";
import { IOwner } from "../SearchForms/GetOwnerForm";
import { UpdateOwnerDTO } from "../../Api/Dto/UpdateOwnerDTO";
import { updateOwner } from "../../Api/Api";
import { OwnerTable } from "../Tables/OwnerTable";

export function UpdateOwnerForm() {

	const [id, setId] = useState(0)
	const [name, setName] = useState('')
	const [date, setDate] = useState('')
	const [submited, setSubmited] = useState(false)
	const [owner, setOwner] = useState<IOwner>({ id: 0, name: '', birthDate: '' })

	const sendOwnerData = async () => {
		const owner: UpdateOwnerDTO = { id: id, name: name, birthDate: date }
		const { data } = await updateOwner(owner)
		setOwner(data)
	}

	const submitHandler = (event: React.FormEvent) => {
		event.preventDefault();
		setSubmited(!submited)
		sendOwnerData()
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

			{submited && <OwnerTable users={[owner]} />}
		</>
	)
}