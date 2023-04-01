import { CloseButton } from "react-bootstrap";
import { CreateCatForm } from "./CreateForms/CreateCatForm";
import { CreateOwnerForm } from "./CreateForms/CreateOwnerForm";
import { DeleteCatForm } from "./DeleteForms/DeleteCatForm";
import { DeleteOwnerForm } from "./DeleteForms/DeleteOwnerForm";
import { GetOwnerForm } from "./SearchForms/GetOwnerForm";
import { UpdateCatForm } from "./UpdateForms/UpdateCatForm";
import { UpdateOwnerForm } from "./UpdateForms/UpdateOwnerForm";
import { GetCatForm } from "./SearchForms/GetCatForm";
import { GetAllOwnersForm } from "./SearchForms/GetAllOwnersForm";
import { GetAllCatsForm } from "./SearchForms/GetAllCatsForm";

interface ModalProps {
	option: string
	onClose: () => void
}

export function Modal({ option, onClose }: ModalProps) {

	const renderForm = () => {
		switch (option) {
			case "Register owner":
				return <CreateOwnerForm />
			case "Register cat":
				return <CreateCatForm />
			case "Update owner":
				return <UpdateOwnerForm />
			case "Update cat":
				return <UpdateCatForm />
			case "Unregister owner":
				return <DeleteOwnerForm />
			case "Unregister cat":
				return <DeleteCatForm />
			case "Search for certain owner":
				return <GetOwnerForm />
			case "Search for certain cat":
				return <GetCatForm />
			case "Search for all owners":
				return <GetAllOwnersForm />
			case "Search for all cats":
				return <GetAllCatsForm />

		}
	}

	return (
		<div>
			<div className="modal-screen"></div>
			<div className="modal-form">
				<CloseButton onClick={onClose} />
				<h2 className="modal-title">{option}</h2>
				{renderForm()}
			</div>
		</div>
	)
}