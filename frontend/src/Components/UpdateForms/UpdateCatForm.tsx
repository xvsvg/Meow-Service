import { useState } from "react"
import { UpdateCatDTO } from "../../Api/Dto/UpdateCatDTO"
import { updateCat } from "../../Api/Api"
import { ICat } from "../SearchForms/GetCatForm"
import { CatTable } from "../Tables/CatTable"
import { Loader } from "../Loaders/Loader"

export function UpdateCatForm() {

	const [catId, setCatId] = useState(0)
	const [name, setName] = useState('')
	const [date, setDate] = useState('')
	const [color, setColor] = useState('')
	const [breed, setBreed] = useState('')
	const [ownerId, setOwnerId] = useState(0)
	const [submited, setSubmited] = useState(false)
	const [cat, setCat] = useState<ICat>({ id: 0, name: '', birthDate: '', color: '', breed: '', owner: { id: 0, birthDate: '', name: '' } })
	const [isLoading, setLoading] = useState(false)

	const sendCatDate = async () => {
		try {
			setLoading(true)
			const cat: UpdateCatDTO = { id: catId, name: name, birthDate: date, color: color, breed: breed, ownerId: ownerId }
			const { data } = await updateCat(cat)
			setCat(data)
		}
		catch (error) {
			console.log("error during sending the request " + error);
		}
		finally {
			setLoading(false)
		}
	}

	const submitHandler = async (event: React.FormEvent) => {
		event.preventDefault();
		setSubmited(!submited)
		await sendCatDate()
	}

	const catIdChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
		setCatId(event.target.valueAsNumber)
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

			{!submited &&
				<form onSubmit={submitHandler}>
					<div className='submit-form-container'>

						<input
							type="number"
							className='input-styles'
							placeholder='enter cat id for update'
							value={catId}
							onChange={catIdChangeHandler}
						/>

						<input
							type="text"
							className='input-styles'
							placeholder='enter updated name'
							value={name}
							onChange={nameChangeHandler}
						/>

						<input
							type="date"
							className='other-input-styles'
							placeholder='enter updated birthdate, yyyy-mm-dd'
							value={date}
							onChange={dateChangeHandler}
						/>

						<input
							type="text"
							className='other-input-styles'
							placeholder='specify updated pet color'
							value={color}
							onChange={colorChangeHandler}
						/>

						<input
							type="text"
							className='other-input-styles'
							placeholder='specify updated pet breed'
							value={breed}
							onChange={breedChangeHandler}
						/>


						<input
							type="number"
							className='other-input-styles'
							placeholder='specify updated owner id'
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