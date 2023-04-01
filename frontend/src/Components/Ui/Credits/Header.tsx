interface HeaderProps {
	headerName: string,
	subheaderName: string
	headerType?: string
	subheaderType?: string
}

export function Header(props: HeaderProps) {
	return (
		<>
			<div className="header">
				<a className="home-page" href="/">
					<h1 className={props.headerType}>{props.headerName}</h1>
				</a>
				<h3 className={props.subheaderType}>{props.subheaderName}</h3>
			</div>
		</>
	)
}
