export interface User {
    id: number;
    username: string;
    password: string;
    role: Roles[];
}

export interface Roles {
	id:	number,
	role: string;
	
}