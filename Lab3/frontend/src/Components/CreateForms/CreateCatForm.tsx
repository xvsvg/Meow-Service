import React, { useState } from 'react'
import { ICat } from '../SearchForms/GetCatForm'
import { CatTable } from '../Tables/CatTable'
import { CreateCatDTO } from '../../Api/Dto/CreateCatDTO'
import { createCat } from '../../Api/Api'
import { Loader } from '../Loaders/Loader'

export function CreateCatForm() {

	const [name, setName] = useState('')
	const [date, setDate] = useState('')
	const [color, setColor] = useState('')
	const [breed, setBreed] = useState('')
	const [ownerId, setOwnerId] = useState(0)
	const [submited, setSubmited] = useState(false)
	const [cat, setCat] = useState<ICat>({ id: 0, name: '', birthDate: '', color: '', breed: '', owner: { id: 0, name: '', birthDate: '' } })
	const [isLoading, setLoading] = useState(false)

	const sendCatData = async () => {
		try {
			setLoading(true)
			const cat: CreateCatDTO = { name: name, birthDate: date, color: color, breed: breed, ownerId: ownerId }
			const { data } = await createCat(cat)
			setCat(data)
		}
		catch (error) {
			console.log("error during sending data " + error);
		}
		finally {
			setLoading(false)
		}
	}

	const submitHandler = async (event: React.FormEvent) => {
		event.preventDefault()
		setSubmited(!submited)
		await sendCatData()
	}

	const nameChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setName(event.target.value)
	}

	const dateChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setDate(event.target.value)
	}

	const colorChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setColor(event.target.value)
	}

	const breedChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setBreed(event.target.value)
	}

	const idChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setOwnerId(event.target.valueAsNumber)
	}

	return (
		<>
			{isLoading && <Loader />}

			{!submited && <form onSubmit={submitHandler}>
				<div className='submit-form-container'>
					<input
						type="text"
						className='input-styles'
						placeholder='enter name'
						value={name}
						onChange={nameChangeHandler}
					/>

					<input
						type="date"
						className='other-input-styles'
						placeholder='enter birthdate, yyyy-mm-dd'
						value={date}
						onChange={dateChangeHandler}
					/>

					<input
						type="text"
						className='other-input-styles'
						placeholder='specify pet color'
						value={color}
						onChange={colorChangeHandler}
					/>

					<input
						type="text"
						className='other-input-styles'
						placeholder='specify pet breed'
						value={breed}
						onChange={breedChangeHandler}
					/>


					<input
						type="number"
						className='other-input-styles'
						placeholder='specify owner id'
						value={ownerId}
						onChange={idChangeHandler}
					/>

					<button type="submit" className="submit-button">submit</button>
				</div>
			</form>}

			{!isLoading && submited && <CatTable cats={[cat]} />}
		</>
	)
}