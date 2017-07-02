export interface  Question {
  id: number;
  question: string;
  typeOfResponse: TypeAnswer[]; 
}


export interface  TypeAnswer {
  id: number;
  type_response: string;
}