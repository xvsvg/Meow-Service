import { RingLoader } from 'react-spinners'

export function Loader() {
	return (
		<RingLoader
			color='#f3ad61'
			size={100}
			cssOverride={{
				marginTop: 50,
				marginLeft: 230
			}}
		/>
	)
}