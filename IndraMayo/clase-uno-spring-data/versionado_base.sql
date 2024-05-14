1) Creamos la tabla de artistas
CREATE TABLE artists (id integer, artist varchar(255) not null, primary key (id));

2) Insertamos los artistas que teniamos
insert into artists  select rowid,artist from songs;

3) Agregamos la columa artists_id a la tabla songs
alter table songs ( artists_id INTEGER REFERENCES artists(id) )

4) Completamos la columna artists_id que creamos antes
update songs set artists_id = (select id from artists where artist=songs.artist)

4) Borramos la columna artists
alter table songs drop column artist