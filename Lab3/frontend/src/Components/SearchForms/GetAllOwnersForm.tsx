import { useEffect, useState } from "react";
import { OwnerTable } from "../Tables/OwnerTable";
import { IOwner } from "./GetOwnerForm";
import { getAllOwners } from "../../Api/Api";
import { Loader } from "../Loaders/Loader";

export interface OwnerPage {
	currentPage: number,
	totalElements: number,
	totalPages: number,
	data: IOwner[]
}

export function GetAllOwnersForm() {

	const [users, setUsers] = useState<IOwner[]>([])
	const [isLoading, setLoading] = useState(false)

	const fetchOwners = async () => {
		try {
			setLoading(true)
			const { data: ownersData } = await getAllOwners();
			setUsers(ownersData.data)
		}
		catch (error) {
			console.log("error fetching users " + error)
		}
		finally {
		}
		setLoading(false)
	}

	useEffect(() => {
		(async () => await fetchOwners())()
	}, [])

	return (
		<>
			{isLoading && <Loader />}
			{!isLoading &&
				<form>
					<div className="submit-form-container">
						<OwnerTable users={users} />
					</div>

				</form>}
		</>
	);
}