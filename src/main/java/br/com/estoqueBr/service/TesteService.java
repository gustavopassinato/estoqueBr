package br.com.estoqueBr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoqueBr.model.Custeio;
import br.com.estoqueBr.model.Destino;
import br.com.estoqueBr.model.Fornecedor;
import br.com.estoqueBr.model.Material;
import br.com.estoqueBr.model.dto.MaterialDto;
import br.com.estoqueBr.service.popula.Populador;
import br.com.estoqueBr.service.register.CusteioRegistrationService;
import br.com.estoqueBr.service.register.DestinoRegistrationService;
import br.com.estoqueBr.service.register.FabricanteRegistrationService;
import br.com.estoqueBr.service.register.FornecedorRegistrationService;
import br.com.estoqueBr.service.register.MaterialRegisterService;
import br.com.estoqueBr.service.register.NotaFiscalRegistrationService;
import br.com.estoqueBr.service.register.OrdemServicoRegistrationService;
import br.com.estoqueBr.service.register.UnidadeMedidaRegistrationService;

@Service
public class TesteService {

	@Autowired
	private CusteioRegistrationService custeioRegistrationService;

	@Autowired
	private DestinoRegistrationService destinoRegistrationService;

	@Autowired
	private FabricanteRegistrationService fabricanteRegistrationService;

	@Autowired
	private FornecedorRegistrationService fornecedorRegistrationService;

	@Autowired
	private NotaFiscalRegistrationService notaFiscalRegistrationService;

	@Autowired
	private OrdemServicoRegistrationService ordemServicoRegistrationService;

	@Autowired
	private UnidadeMedidaRegistrationService unidadeMedidaRegistrationService;
	
	@Autowired
	private MaterialRegisterService materialRegisterService;

	public void teste(Populador populador) {
//		Custeio custeio = custeioRegistrationService.createCusteio(populador.getCusteioNome());
//		Destino destino = destinoRegistrationService.create(populador.getDestinoNome());
//		Fornecedor fornecedor = fornecedorRegistrationService.create(populador.getFornecedorNome(),
//				populador.getFornecedorCnpj());
//
//		fabricanteRegistrationService.create(populador.getFabricanteNome());
//		notaFiscalRegistrationService.create(populador.getNotaNumero(), destino, fornecedor, custeio);
//		unidadeMedidaRegistrationService.create(populador.getUnidMedNome(), populador.getUnidMedAbrev());
//		ordemServicoRegistrationService.create(populador.getOrdemServicoNumero());
	}
	
	public void cadastroMaterial(MaterialDto mateiralForm) {
		materialRegisterService.create(mateiralForm);
	}
}
