ROMANCE, COMEDIA, SUSPENSE, ACAO, TERROR, DRAMA, FICCAO_CIENTIFICA,GUERRA,FANTASIA,ANIMACAO,AVENTURA;

List<Genero> g = new ArrayList<>();
g.add(Genero.ACAO);
g.add(Genero.SUSPENSE);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(1,"TRUQUE DE MESTRE O SEGUNDO ATO",g ,"Jon M. Chu",LocalDate.of(2016, 05, 9),"Inglês")));
g.clear();
g.add(Genero.ACAO);
g.add(Genero.FICCAO_CIENTIFICA);
g.add(Genero.GUERRA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(2,"TRANSFORMERS", g,"Michael Bay",LocalDate.of(2007, 06, 20),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(3,"TRANSFORMERS - A VINGANÇA DOS DERROTADOS", g,"Michael Bay",LocalDate.of(2009, 05, 24),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(4,"TRANSFORMERS: O LADO OCULTO DA LUA", g,"Michael Bay",LocalDate.of(2011, 06, 01),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(5,"TRANSFORMERS: A ERA DA EXTINÇÃO", g,"Michael Bay",LocalDate.of(2014, 06, 17),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(6,"TRANSFORMERS: O ÚLTIMO CAVALEIRO", g,"Michael Bay",LocalDate.of(2017, 07, 20),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(7,"BATMAN - O CAVALEIRO DAS TREVAS", g,"Christopher Nolan",LocalDate.of(2008, 07, 18),"Inglês")));
g.clear();
g.add(Genero.SUSPENSE);
g.add(Genero.FICCAO_CIENTIFICA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(8,"MATRIX", g,"Lana Wachowski, Lilly Wachowski",LocalDate.of(1999, 03, 21),"Inglês")));
g.clear();
g.add(Genero.AVENTURA);
g.add(Genero.ANIMACAO);
g.add(Genero.DRAMA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(9,"O REI LEÃO",g ,"Roger Allers, Rob Minkoff",LocalDate.of(2011, 8, 26),"Inglês")));
g.clear();
g.add(Genero.FANTASIA);
g.add(Genero.AVENTURA);
g.add(Genero.DRAMA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(10,"HARRY POTTER E A PEDRA FILOSOFAL",g ,"Chris Columbus",LocalDate.of(2001, 11, 23),"Inglês")));
g.clear();
g.add(Genero.ANIMACAO);
g.add(Genero.COMEDIA);
g.add(Genero.AVENTURA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(11,"COMO TREINAR O SEU DRAGÃO 2", g,"Dean DeBlois",LocalDate.of(2014, 05, 19),"Inglês")));
g.clear();
g.add(Genero.COMEDIA);
g.add(Genero.DRAMA);
g.add(Genero.ROMANCE);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(12,"O AUTO DA COMPADECIDA", g,"Guel Arraes",LocalDate.of(2000, 9, 10),"Português")));
g.clear();
g.add(Genero.COMEDIA);
g.add(Genero.ACAO);
g.add(Genero.AVENTURA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(13,"DEADPOOL", g,"Tim Miller",LocalDate.of(2016, 02, 11),"Inglês")));
g.clear();
g.add(Genero.ANIMACAO);
g.add(Genero.COMEDIA);
g.add(Genero.AVENTURA]);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(14,"ZOOTOPIA: ESSA CIDADE É O BICHO", g,"Byron Howard, Rich Moore",LocalDate.of(2016, 03, 17),"Inglês")));
g.clear();
g.add(Genero.FICCAO_CIENTIFICA);
g.add(Genero.ROMANCE);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(15,"FORREST GUMP - O CONTADOR DE HISTÓRIAS", g,"Robert Zemeckis",LocalDate.of(1994, 12, 07),"Inglês")));
g.clear();
g.add(Genero.ROMANCE);
g.add(Genero.COMEDIA);
g.add(Genero.DRAMA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(16,"ORGULHO E PRECONCEITO", g,"Joe Wright",LocalDate.of(2006, 02, 10),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(16,"DIÁRIO DE UMA PAIXÃO", g,"Nick Cassavetes",LocalDate.of(2004, 8, 13),"Inglês")));
g.clear();
g.add(Genero.DRAMA);
g.add(Genero.AVENTURA);
g.add(Genero.ROMANCE);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(17,"TEMPOS MODERNOS",g ,"Charles Chaplin",LocalDate.of(1936, 02, 05),"Inglês")));
g.clear();
g.add(Genero.ANIMACAO);
g.add(Genero.COMEDIA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(18,"Paddington 2", g,"Paul King",LocalDate.of(2017, 04, 05),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(19,"Olaf: Em Uma Nova Aventura Congelante De Frozen", g,"Kevin Deters, Stevie Wermers",LocalDate.of(2017, 06, 21),"Inglês")));
g.clear();
g.add(Genero.ANIMACAO);
g.add(Genero.AVENTURA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(20,"O Poderoso Chefinho", g,"Tom McGrath",LocalDate.of(2017, 02, 05),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(21,"Moana: Um Mar De Aventuras", g,"John Musker, Ron Clements",LocalDate.of(2016, 07, 14),"Inglês")));
g.clear();
g.add(Genero.DRAMA);
g.add(Genero.ROMANCE);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(22,"O Livro De Henry", g,"Colin Trevorrow",LocalDate.of(2017, 01, 14),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(23,"À PROCURA DA FELICIDADE", g,"Gabriele Muccino",LocalDate.of(2007, 02, 02),"Inglês")));
g.clear();
g.add(Genero.ACAO);
g.add(Genero.GUERRA);
g.add(Genero.DRAMA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(24,"SNIPER AMERICANO", g,"Clint Eastwood",LocalDate.of(2015, 02, 19),"Inglês")));
g.clear();
g.add(Genero.DRAMA);
g.add(Genero.ROMANCE);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(25,"A TEORIA DE TUDO", g,"James Marsh",LocalDate.of(2015, 01, 29),"Inglês")));
g.clear();
g.add(Genero.ROMANCE);
g.add(Genero.DRAMA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(26,"PARA SEMRPE",g ,"Michael Sucsy",LocalDate.of(2012, 05, 07),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(27,"COMO EU ERA ANTES DE VOCÊ", g,"Thea Sharrock",LocalDate.of(2016, 05, 16),"Inglês")));
g.clear();
g.add(Genero.ROMANCE);
g.add(Genero.GUERRA);
g.add(Genero.DRAMA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(28,"O MENINO DO PIJAMA LISTRADO", g,"Mark Herman",LocalDate.of(2008, 12, 12),"Inglês")));
g.clear();
g.add(Genero.ROMANCE);
g.add(Genero.DRAMA);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(29,"TITANIC", g,"James Cameron",LocalDate.of(2012, 04, 13),"Inglês")));
g.clear();
g.add(Genero.TERROR);
g.add(Genero.SUSPENSE);
System.out.println(GerenciaFilme.adicionarFilme(new Filme(30,"IT - A COISA", g,"Andy Muschietti",LocalDate.of(2017, 9, 07),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(31,"ÁGUAS RASAS", g,"Jaume Collet-Serra",LocalDate.of(2016, 8, 25),"Inglês")));
System.out.println(GerenciaFilme.adicionarFilme(new Filme(32,"INVOCAÇÃO DO MAL", g,"James Wan",LocalDate.of(2013, 9, 13),"Inglês")));



//Um usuario avaliou um filme acao com 10 e o resto de suas experiencias positivas e de filmes de romance: problema