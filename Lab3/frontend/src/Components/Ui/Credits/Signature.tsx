import React from 'react'

interface SignatureProps{
	text: string
}

const Signature = ({text} : SignatureProps) => {
	return (
		<div className="signature">
			<p className="signature-text">{text}</p>
		</div>
	)
}

export default Signature