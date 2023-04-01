import { FC } from "react";
import { Header } from "./Components/Ui/Credits/Header";
import { Modal } from "./Components/Modal";
import UiDropdown from "./Components/Ui/Buttons/UiDropdown";
import Signature from "./Components/Ui/Credits/Signature";
import './Styles/App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import useButton from "./Hooks/useButton";

const commonData = ["owner", "cat"]
const getData = ["certain owner", "certain cat", "all owners", "all cats"]

const App: FC = () => {

	const [showCreateModal, createFormStatus, setShowCreateModal, setCreateFormStatus] = useButton();
	const [showUpdateModal, updateFormStatus, setShowUpdateModal, setUpdateFormStatus] = useButton();
	const [showGetModal, getFormStatus, setShowGetModal, setGetFormStatus] = useButton();
	const [showDeleteModal, deleteFormStatus, setShowDeleteModal, setDeleteFormStatus] = useButton();

	const handleCreateButtonClick = (event: React.MouseEvent<HTMLButtonElement>) => {
		setShowCreateModal(event.currentTarget.getAttribute("name") as string)
		setCreateFormStatus(!createFormStatus)
	}

	const handleUpdateButtonClick = (event: React.MouseEvent<HTMLButtonElement>) => {
		setShowUpdateModal(event.currentTarget.getAttribute("name") as string)
		setUpdateFormStatus(!updateFormStatus)
	}

	const handleDeleteButtonClick = (event: React.MouseEvent<HTMLButtonElement>) => {
		setShowDeleteModal(event.currentTarget.getAttribute("name") as string)
		setDeleteFormStatus(!deleteFormStatus)
	}

	const handleGetButtonClick = (event: React.MouseEvent<HTMLButtonElement>) => {
		setShowGetModal(event.currentTarget.getAttribute("name") as string)
		setGetFormStatus(!getFormStatus)
	}

	return (
		<>
			<Header headerType="header"
				headerName="Meow Service 🐈"
				subheaderType="subheader"
				subheaderName="all in one place"
			/>

			<div className="horizontal-container">
				<UiDropdown items={commonData} title="Register" onClick={handleCreateButtonClick} />
				<UiDropdown items={commonData} title="Update" onClick={handleUpdateButtonClick} />
				<UiDropdown items={getData} title="Search for" onClick={handleGetButtonClick} />
				<UiDropdown items={commonData} title="Unregister" onClick={handleDeleteButtonClick} />
			</div>

			{createFormStatus && <Modal
				option={"Register " + showCreateModal}
				onClose={() => setCreateFormStatus(!createFormStatus)}
			/>}

			{updateFormStatus && <Modal
				option={"Update " + showUpdateModal}
				onClose={() => setUpdateFormStatus(!updateFormStatus)}
			/>}

			{getFormStatus && <Modal
				option={"Search for " + showGetModal}
				onClose={() => setGetFormStatus(!getFormStatus)} />}

			{deleteFormStatus && <Modal
				option={"Unregister " + showDeleteModal}
				onClose={() => setDeleteFormStatus(!deleteFormStatus)}
			/>}


			<Signature text="made with love to cats💕" />
		</>
	);
}

export default App;
