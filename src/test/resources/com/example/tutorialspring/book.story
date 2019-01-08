Meta:

Narrative:
As a user
I want to look up a non-existent user's profile on github
So that I can be sure that the username can not be found on github

Scenario: when a user checks a non-existent user on github, github would respond 'not found'

Given book api
And a correctBook to be registered
When I register the random book
Then Random book is exists