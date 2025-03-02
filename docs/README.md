# Fairy User Guide

![Ui.png]

Fairy is a personal assistant that can help you manage tasks.

There are 4 kinds of tasks that our chatbot can handle:

- **ToDo**: normal task with no time attributes.
- **Deadline**: task with an end time.
- **Event**: task with start time and end time.
- **Fixed Duration**: task with no specific start and end time but with specific duration.

Fairy also supports marking tasks as done, and search task by name or date.

## Features

### Adding a todo task: `todo`

Adds a todo task to the list.

Format: `todo NAME`

Example: `todo CS2103T HW`

### Adding a deadline task: `deadline`

Adds a deadline task to the list.

Format: `deadline NAME /by YYYYMMDD HHMM`

Example: `deadline CS2103T HW /by 20250101 0000`

### Adding an event task: `event`

Adds an event to the list.

Format: `event NAME /from YYYYMMDD HHMM /to YYYYMMDD HHMM`

Example: `event CS2103T field trip /from 20250101 0000 /to 20250101 2359`

### Adding a fixed duration task: `fixdur`

Adds a fixed duration task to the list.

Format: `fixdur NAME /dur DURATION`

- Duration must be a **positive integer**.

Example: `fixdur Jogging /dur 1` adds a fixed duration task `Jogging` with duration `1` hour.

### Deleting a task: `delete`

Deletes a task from the list.

Format: `delete INDEX`

- The index starts from 1. Lookup for the list of indexed tasks using `list` command.

Example: `delete 6` deletes the 6th task in the list.

### Marking a task as completed: `mark`

Marks a task from the list as completed.

Format: `mark INDEX`

- The index starts from 1. Lookup for the list of indexed tasks using `list` command.

Example: `mark 6` marks the 6th task in the list as completed.

### Marking a task as incomplete: `unmark`

Marks a task from the list as completed.

Format: `unmark INDEX`

- The index starts from 1. Lookup for the list of indexed tasks using `list` command.

Example: `unmark 6` marks the 6th task in the list as incomplete.

### Listing all the tasks in the list: `list`

Lists all the tasks in the list with index attached.

Format: `list`

