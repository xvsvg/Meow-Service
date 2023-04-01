import React, { useState } from "react";
import { createOwner } from "../../Api/Api";
import { CreateOwnerDTO } from "../../Api/Dto/CreateOwnerDTO";
import { OwnerTable } from "../Tables/OwnerTable";
import { IOwner } from "../SearchForms/GetOwnerForm";
import { Loader } from "../Loaders/Loader";

export function CreateOwnerForm() {

	const [name, setName] = useState('')
	const [date, setDate] = useState('')
	const [submited, setSubmited] = useState(false)
	const [owner, setOwner] = useState<IOwner>({ id: 0, name: '', birthDate: '' })
	const [isLoading, setLoading] = useState(false)

	const sendOwnerData = async () => {
		try {
			setLoading(true)
			const owner: CreateOwnerDTO = { name: name, birthDate: date }
			const { data } = await createOwner(owner)
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
		await sendOwnerData();
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

			{!submited && <form onSubmit={submitHandler}>
				<div className="submit-form-container">
					<input
						type="text"
						className="input-styles"
						placeholder="enter name"
						value={name}
						onChange={nameChangeHandler}
					/>

					<input
						type="date"
						className="other-input-styles"
						placeholder="enter birthdate, yyyy-mm-dd"
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