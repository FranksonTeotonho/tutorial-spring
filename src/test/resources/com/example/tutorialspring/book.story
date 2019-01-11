Meta:

Narrative:
As a user
I want to look up a non-existent user's profile on github
So that I can be sure that the username can not be found on github

Scenario: User should be able to create

Given book api
When I create a book with title <title> and author <author>

Examples:
|title             |author              |
|Dom_Quixote       |Miguel_de_Cervantes |
|Guerra_e_Paz      |Liev_Tolstoi        |
|A_Montanha_Magica |Thomas_Mann         |

Scenario: User should be able to search books by title

When I search a book by title <title>
Then book with title <title> should be returned
Examples:
|title             |
|Dom_Quixote       |
|Guerra_e_Paz      |
|A_Montanha_Magica |


Scenario: Book api returns correct author for each book

When author of book with title <title> should be <author>
Examples:
|title             |author              |
|Dom_Quixote       |Miguel_de_Cervantes |
|A_Montanha_Magica |Thomas_Mann         |

Scenario: User should be able to delete and count existing books in the database

Given book api
When I delete all books
Then total of books should be 0

When I create a book with title Cem_Anos_de_Solidao and author Gabriel_Garcia_Marquez
When I create a book with title Ulisses and author James_Joyce
When I create a book with title A_Divina_Comedia and author Dante_Alighieri
Then total of books should be 3

When I delete book with title Cem_Anos_de_Solidao
Then total of books should be 2