
CREATE DATABASE IF NOT EXISTS CITY_EVENTS default charset utf8 COLLATE utf8_general_ci;

------------------------------------------------------------------------------------------------

// Created in Database
CREATE TABLE `EVENTS` (
  ID              INT            NOT NULL AUTO_INCREMENT, 
  TITLE           VARCHAR(100)   NOT NULL, 
  START_TIME      DATETIME       NOT NULL,
  END_TIME        DATETIME       NOT NULL, 
  LOCATION        VARCHAR(100)   NOT NULL,
  SUMMARY         VARCHAR(500)   NOT NULL,
  COST            INT            NOT NULL DEFAULT 0, 
  CATEGORY        INT, 
  ORGANIZER       INT            NOT NULL,
  CREATOR         INT            NOT NULL, 
  TS_CREATED      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  TS_UPDATED      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

Example:
INSERT INTO EVENT( TITLE, E_DATE, STA_TIME, END_TIME, LOCATION, SUMMARY, COST, CATEGORY, ORGANIZER, CREATER ) 
VALUES (
  'Guitar play, live concert', '2016-03-31 14:00', '2016-03-31 17:00', 'MountainView velly No.1 Street', 'Small palace concert from Norah Jones', 0, 1, 1, 1
);

------------------------------------------------------------------------------------------------

CREATE TABLE `ORGANIZERS` (
  ID              INT                    NOT NULL AUTO_INCREMENT, 
  NAME            VARCHAR(50)            NOT NULL, 
  GENDER          ENUM( 'M', 'F', 'O' )  NOT NULL, 
  PHONE           CHAR(20)               NOT NULL, 
  EMAIL           VARCHAR(50)            NOT NULL UNIQUE, 
  WEBSITE         VARCHAR(30),
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

Example:
INSERT INTO ORGANIZER( NAME, GENDER, PHONE, E_MAIL, WEB_SITE ) VALUES ( 'Kelly Smith', 'F', '+040 90876576',    'kelly.smith@gmail.com' 'www.springfestivalhd.com' );
INSERT INTO ORGANIZER( NAME, GENDER, PHONE, E_MAIL, WEB_SITE ) VALUES ( 'John Smith',  'M', '+086 13008765476', 'john.smith@gmail.com'  'www.springfestivalhd.com' );

------------------------------------------------------------------------------------------------

CREATE TABLE `USERS` (
  ID              INT                   NOT NULL AUTO_INCREMENT,
  EMAIL           VARCHAR(50)           NOT NULL UNIQUE,
  PASSWORD        CHAR(41)              NOT NULL,
  PHONE           CHAR(20),
  NAME            VARCHAR(30)           NOT NULL,
  GENDER          ENUM( 'M', 'F', 'O' ) NOT NULL,
  FACEBOOK        VARCHAR(30),
  TWITTER         VARCHAR(30),
  LINKEDIN        VARCHAR(60),
  WECHAT          VARCHAR(40),
  TS_REGISTERED   TIMESTAMP             NOT NULL DEFAULT CURRENT_TIMESTAMP,
  TS_UPDATED      TIMESTAMP             NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

Example:
INSERT INTO USERS( EMAIL, PASSWORD, PHONE, NAME, GENDER ) VALUES ( 'kelly.smith@gmail.com', PASSWORD('123456'), '+040 90876576',    'Kelly Smith', 'F' );
INSERT INTO USERS( EMAIL, PASSWORD, PHONE, NAME, GENDER ) VALUES ( 'john.smith@gmail.com',  PASSWORD('123456'), '+086 13008765476', 'John Smith',  'M' );

------------------------------------------------------------------------------------------------

CREATE TABLE `CATEGORIES` (
  ID              TINYINT        NOT NULL AUTO_INCREMENT,
  NAME            VARCHAR(50)    NOT NULL,
  DESCRIPTION     VARCHAR(100)   NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT charset=utf8 AUTO_INCREMENT=1;

Example:
INSERT INTO CATEGORIES ( NAME, DESCRIPTION ) VALUES ( 'Social', 'Social activities' );
INSERT INTO CATEGORIES ( NAME, DESCRIPTION ) VALUES ( 'Sports', 'Sports activities at some place...' );

------------------------------------------------------------------------------------------------

CREATE TABLE `TAGS` (
  ID             INT            NOT NULL AUTO_INCREMENT,
  NAME           VARCHAR(20)    NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

Example:
INSERT INTO TAGS( NAME ) VALUES ( '90s' );
INSERT INTO TAGS( NAME ) VALUES ( 'country music' );

------------------------------------------------------------------------------------------------

CREATE TABLE `EVENT_TAG` (
  EVENT_ID      INT           NOT NULL,
  TAG_ID        INT           NOT NULL,
  PRIMARY KEY( `EVENT_ID`, `TAG_ID` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

Example:
INSERT INTO EVENT_TAG( EVENT_ID, TAG_ID ) VALUES ( 1, 1 );
INSERT INTO EVENT_TAG( EVENT_ID, TAG_ID ) VALUES ( 1, 2 );

------------------------------------------------------------------------------------------------
