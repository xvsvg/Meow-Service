import { Table } from "react-bootstrap";
import { ICat } from "../SearchForms/GetCatForm";

interface CatTableProps {
	cats: ICat[]
}

export function CatTable({ cats }: CatTableProps) {
	return (
		<>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Birhdate</th>
						<th>Breed</th>
						<th>Color</th>
						<th>OwnerID</th>
						<th>Owner name</th>
					</tr>
				</thead>
				<tbody>
					{cats && cats.map((cat) => (
						<tr key={cat.id}>
							<td>{cat.id}</td>
							<td>{cat.name}</td>
							<td>{cat.birthDate}</td>
							<td>{cat.breed}</td>
							<td>{cat.color}</td>
							<td>{cat.owner.id}</td>
							<td>{cat.owner.name}</td>
						</tr>
					))}
				</tbody>
			</Table>
		</>
	)
}