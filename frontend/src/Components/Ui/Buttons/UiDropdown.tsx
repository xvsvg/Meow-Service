import React from 'react'
import { Dropdown, DropdownButton } from 'react-bootstrap'

interface UiDropdownProps {
	items: JSX.Element[] | string[],
	title: string
	onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void
}

const UiDropdown = ({ items, title, onClick }: UiDropdownProps) => {
	return (
		<DropdownButton title={title}>
			{items && items.map(item => <Dropdown.Item key={item.toString()} name={item} onClick={onClick}>{item}</Dropdown.Item>)}
		</DropdownButton>
	)
}

export default UiDropdown