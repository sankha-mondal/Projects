export interface Category {

     id: number;
     categoryName: string;
     description: string;
     available: boolean;
     books: Books;
}
interface Books {

    id: number;
    bookName: string;
 }
