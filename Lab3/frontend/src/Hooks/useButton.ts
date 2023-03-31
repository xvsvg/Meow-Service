import { useState } from "react"

type CreateOptionClosure = () => [string, boolean, React.Dispatch<React.SetStateAction<string>>, React.Dispatch<React.SetStateAction<boolean>>];

const useButton: CreateOptionClosure = () => {
	const [createOption, setCreateOption] = useState('')
	const [disabled, setDisabled] = useState(false)

	const setOption: React.Dispatch<React.SetStateAction<string>> = (value: React.SetStateAction<string>) => {
		setCreateOption(value)
	}

	const updateView: React.Dispatch<React.SetStateAction<boolean>> = () => {
		setDisabled(!disabled)
	}

	return [createOption, disabled, setOption, updateView]
}

export default useButton