## Description

The `Menu` class gives the user a simple command-line interface to add, remove, and list the `AddressEntries` from an `AddressBook`.

## State of System

### Menu
- [x] Displays menu
- [x] loads entries from file
- [x] can add entries by entering them via the command line
- [x] can remove entries that start with some last name
- [x] find entries that start with some last name
- [x] list entries

### AddressBook
- [x] add entry
- [x] remove entry
- [x] find entries that start with some last name
- [x] list entries
- [x] add entries from a text file

## List of Classes

* `AddressBookApplication` is the entry point of the application and calls some of the `Menu` class methods.

* `AddressEntry` contains fields related to a person's contact information, such as their name, street address, phone number, and email address.

* `AddressBook` is a collection of `AddressEntry`. The `AddressBook` uses a `TreeSet` of `AddressEntries`, so that the main operations (adding, removing, and searching address entries) is `O(log n)`. The `AddressBook` also does not support entries with duplicate last and first names.

* `Menu` lets the user interact with the `AddressBook` through the console. The Menu interface lets the user add, remove, find, and list the address entries in the address book.

# Javadoc URL

[Link to Javadoc](https://lordarugula.github.io/AddressBook/docs/index.html)

## UML/Design

### UML Class Hierarchy Diagram
Showing Hierarchy and Cardinality and Object Associations for this project.

![Address Book Application UML diagram](Address%20UML.svg)

## Working Screenshots

Screenshots showing your Java Application working (capture screen dumps into a word document) for the following cases:

### Work Instance 1

Read in entries from database followed by listing. The database must contain a minimum of 5 addresses. Place screenshots of database table and output shown in GUI of program here.

### Work Instance 2

Immediately following D.1 do an addition of new `AddressEntry` object followed by a listing.

Show result in `ADDRESSBOOKENTRY` database table.

Place screenshots of UPDATED (has a new entry) database table AND output shown in GUI of program here.

### Work Instance 3

Immediately following D.2 do a removal of an entry followed by a listing

Place screenshots of UPDATED (has removed an entry) `ADDRESSBOOKENTRY` database table AND output shown in GUI of program here.

### Work Instance 4

Immediately following D.3 do a find using input that should retrieve at least one entry. Now do a find using input that should retrieve no entries. Place screenshots of each find here.

## Commit History
Screen shots showing the GitHub repository commits, Show Code tree

## Demonstrate Project
Create YouTube video and give link in this section
