import { useState } from "react"
import { ICat } from "../SearchForms/GetCatForm";
import { deleteCat } from "../../Api/Api";
import { CatTable } from "../Tables/CatTable";
import { Loader } from "../Loaders/Loader";

export function DeleteCatForm() {

	const [id, setId] = useState(0)
	const [submited, setSubmited] = useState(false)
	const [cat, setCat] = useState<ICat>({ id: 0, name: '', birthDate: '', color: '', breed: '', owner: { id: 0, name: '', birthDate: '' } })
	const [isLoading, setLoading] = useState(false)

	const sendCatData = async () => {
		try {
			setLoading(true)
			const { data } = await deleteCat(id)
			setCat(data)
		}
		catch (error) {
			console.log("error during sending request" + error);
		}
		finally {
			setLoading(false)
		}
	}

	const submitHandler = async (event: React.FormEvent) => {
		event.preventDefault();
		setSubmited(!submited)
		await sendCatData()
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

			{!isLoading && submited && <CatTable cats={[cat]} />}
		</>
	)
}