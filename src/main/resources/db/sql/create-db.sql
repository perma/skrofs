CREATE TABLE Player (
  PlayerId  INTEGER PRIMARY KEY IDENTITY,
  Firstname VARCHAR(30),
  Surname   VARCHAR(50),
  Gender    VARCHAR(1),
  Birthday  DATE
);

CREATE TABLE Event (
  EventId   INTEGER PRIMARY KEY IDENTITY,
  NumberOfPlayers INTEGER,
  StartTime TIMESTAMP,
  EndTime   TIMESTAMP
);

CREATE TABLE PlayerEvent (
  PlayerId  INTEGER FOREIGN KEY REFERENCES Player(PlayerId),
  EventId   INTEGER FOREIGN KEY REFERENCES Event(EventId),
  Round3    INTEGER,
  Round4    INTEGER,
  Round5    INTEGER,
  Round6    INTEGER,
  Round7    INTEGER,
  Round8    INTEGER,
  Round9    INTEGER,
  Round10   INTEGER,
  Round11   INTEGER,
  Round12   INTEGER,
  Round13   INTEGER,
  Round14   INTEGER
);
