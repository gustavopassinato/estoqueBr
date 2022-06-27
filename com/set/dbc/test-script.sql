select m.nome,
	sum(e.quantidade) as quantidade
from entrada e
join material m on (m.codigo = e.id_material)
group by m.codigo;

select * from entrada;

select d.nome,
	sum(e.quantidade),
    m.nome
from entrada e 
join material m on (m.codigo = e.id_material)
join nota_fiscal nf on (nf.id = e.id_nota)
join destino d on (nf.id_destino = d.id);


alter table nota_fiscal 
add constraint nota_fiscal_constraint unique (numero);


