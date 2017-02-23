import {em, normalizeW, normalizeH, normalizeBorder} from './Responsive'

export default THEME = {
	base: {
		color: '#030303',
		linkColor: '#50E3C2',
		backgroundColor: '#fff',
		fontSize: {
			base: em(14),
			large: em(18),
			small: em(11)
		},
		input: {
			height: normalizeH(50),
			paddingLeft: normalizeW(10),
			paddingRight: normalizeW(10),
			backgroundColor: '#F3F3F3',
			borderWidth: normalizeBorder(),
    	borderColor: '#E9E9E9',
    	fontSize: em(16),
    	color: '#B2B2B2'
		},
		inputContainer: {
			marginLeft: 0,
	    marginRight: 0,
	    marginBottom: 0,
	    marginTop: 0,
	    borderBottomWidth: 0,
			paddingLeft: normalizeW(17),
			paddingRight: normalizeW(17),
		}
	},
	colors: {
		white: '#fff',
		lessWhite: '#f2f2f2',
		black: '#000',
		dark: '#636363',
		lessDark: '#686868',
		light: '#B2B2B2',
		lighter: '#B5B5B5',
		lighterA: '#E6E6E6',
		lighterB: '#d8d8d8',
		lightest: '#E9E9E9',
		green: '#50E3C2',
    gray: '#929292',
    subDark: '#232323',
		inputLabel: '#656565',
		red: '#F24016'
	},
}

export const INNER_CSS =
`
body {
	color: #555;
	font-size: 16px;
	line-height: 1.7;
	text-align: justify;
}
img {
	display: block;
	margin: 15px auto;
	max-width: 100%;
}
p ,div {
	margin: 0;
	color: #555;
	font-size: 16px;
	line-height: 1.7;
	text-align: justify;
}
h1,h2,h3,h4,h5 {
	margin: 0;
	line-height: 1.7;
}
h1 {
	font-size: 22px;
}
h2 {
	font-size: 20px;
}
h3 {
	font-size: 18px;
}
h4 {
	font-size: 16px;
}
h5 {
	font-size: 14px;
}
ul ,ol {
	margin: 0;
}
ul li ,ol li {
	line-height: 1.7;
	list-style: none;
}
blockquote {
	display: block;
	padding: 12px 10px;
	margin: 15px 0;
	line-height: 1.7;
	font-size: 100%;
	border-left: 4px solid #D1E7EE;
	background-color: #f5f5f5;
}
blockquote p {
	margin: 0 0 0 6px;
}`
