import { Table } from "react-bootstrap";
import { IOwner } from "../SearchForms/GetOwnerForm";
import { Loader } from "../Loaders/Loader";

interface OwnerTableProps {
	users: IOwner[]
}

export function OwnerTable({ users }: OwnerTableProps) {
	return (
		<>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
					{users.map((user) => (
						<tr key={user.id}>
							<td>{user.id}</td>
							<td>{user.name}</td>
							<td>{user.birthDate}</td>
						</tr>
					))}
				</tbody>
			</Table>
		</>
	)
}