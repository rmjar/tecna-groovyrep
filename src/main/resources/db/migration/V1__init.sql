create table groovyrep (id identity not null, name varchar(255), script clob, primary key (id), unique(name));

insert into groovyrep(id, name, script) values ( 1, 'Demo1.groovy' , 'println(\"Hello World\");' );
insert into groovyrep(id, name, script) values ( 2, 'Demo2.groovy' , 'static void main(String[] args) {\n        println \"Hello World\"\n}' );
insert into groovyrep(id, name, script) values ( 3, 'Demo3.groovy' , 'class Demo {\n    static void main(String[] args) {\n        println \"Hello World\"\n}\n}' );
insert into groovyrep(id, name, script) values ( 4, 'Demo4.groovy' , 'def x = \"\"\"Groovy\nat\nGuru99\"\"\"\nprintln x' );
insert into groovyrep(id, name, script) values ( 5, 'Demo5.groovy' , 'class Demo {\nstatic def a;\nstatic def b;\n    static void main(String[] args) {\n        println a.toDouble() + b.toDouble()\n    }\n}' );
insert into groovyrep(id, name, script) values ( 6, 'Demo6.groovy' , 'a.toDouble()+b.toDouble()' );