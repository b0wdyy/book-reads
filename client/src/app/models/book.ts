export interface Book {
  uuid: string;
  title: string;
  author: string;
  genre: string;
  publisher: string;
  year: string;
  isbn: string;
  pages: number;
  description: string;
  image?: string;
  rating: number;
}
