CREATE TABLE users (
	id BIGSERIAL PRIMARY KEY,
	login varchar(100) UNIQUE, 
	email varchar(100) UNIQUE, 
	password varchar(100) NOT NULL, 
	registered TIMESTAMP WITH TIME ZONE, 
	logged TIMESTAMP WITH TIME ZONE
);

INSERT INTO users (login, email, password, registered, logged) 
	VALUES ('admin', 'admin@gmail.com', '21232f297a57a5a743894a0e4a801fc3', NOW(), NOW());

CREATE TABLE pages (
	id BIGSERIAL PRIMARY KEY,
	index varchar(100) UNIQUE, 
	title varchar(1000), 
	image text, 
	intro text, 
	content text NOT NULL, 
	modified TIMESTAMP WITH TIME ZONE
);

INSERT INTO pages (index, title, image, intro, content, modified) 
	VALUES ('index', 'Start', 'https://images.unsplash.com/photo-1498496294664-d9372eb521f3?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=f81344f21400a387f61effd4b5a4dcd5&auto=format&fit=crop&w=500&q=60', 'Welcome!', 'This is starting page of Your Blog.', NOW());
	
INSERT INTO pages (index, title, image, intro, content, modified) 
	VALUES ('contact', 'Contact', 'https://images.unsplash.com/1/iphone-4-closeup.jpg?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=3793dc829da718fc56ef19a8c99d938b&auto=format&fit=crop&w=500&q=60', 'Contact me!', 'This is contact page of Your Blog.', NOW());
	
INSERT INTO pages (index, title, image, intro, content, modified) 
	VALUES ('article-1', 'My first article', 'https://images.unsplash.com/photo-1461773518188-b3e86f98242f?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=b53051efbc0883b392aeaf9ad1a96ad7&auto=format&fit=crop&w=500&q=60', 'Intro to my first article...', 'This is content of my first article...', NOW());
	
INSERT INTO pages (index, title, image, intro, content, modified) 
	VALUES ('article-2', 'My second article', 'https://images.unsplash.com/photo-1518081461904-9d8f136351c2?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=8a815e999d0f593c8c8bbcb6473b0d39&auto=format&fit=crop&w=500&q=60', 'Intro to my second article...', 'This is content of my second article...', NOW());
	
CREATE TABLE messages (
	id BIGSERIAL PRIMARY KEY,
	nick varchar(100) NOT NULL, 
	email varchar(100) NOT NULL, 
	message text NOT NULL, 
	ip varchar(15), 
	request boolean, 
	sent TIMESTAMP WITH TIME ZONE
);

CREATE TABLE visitors (
	id BIGSERIAL PRIMARY KEY,
	visitor_ip varchar(15), 
	http_referer text, 
	request_uri text, 
	visited TIMESTAMP WITH TIME ZONE
);

