# task-done-coordinator

A spring boot application that keeps track of groups of tasks to determine when the entire group of tasks are finished.

It will do that in three steps:

1. Accept task registrations. A task will belong to a Task Group.

2. Accept when Tasks are done. A task done message will say which Task, Task Group, and Process Group.

3. Accept queries to determine if all sub-tasks are done (based on Task Group and Process Group).
