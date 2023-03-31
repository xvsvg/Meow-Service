import { useEffect, useState } from "react";
import { OwnerTable } from "../Tables/OwnerTable";
import { IOwner } from "./GetOwnerForm";
import { getAllOwners } from "../../Api/Api";

export interface OwnerPage {
	currentPage: number,
	totalElements: number,
	totalPages: number,
	data: IOwner[]
}

export function GetAllOwnersForm() {
	
	const [users, setUsers] = useState<IOwner[]>([])

	const fetchOwners = async () => {
		try {
			const { data: ownersData } = await getAllOwners();
			setUsers(ownersData.data)
		}
		catch (error) {
			console.log("error fetching users " + error)
		}
	}

	useEffect(() => {
		(async () => await fetchOwners())()
	}, [])

	return (
		<>
			{users &&
				<form>
					<div className="submit-form-container">
						<OwnerTable users={users} />
					</div>

				</form>}
		</>
	);
}