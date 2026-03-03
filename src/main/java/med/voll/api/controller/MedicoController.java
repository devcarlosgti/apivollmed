package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

//    @PostMapping
//    @Transactional
//    public void cadastrar(@RequestBody @Valid  DadosCadastroMedico dados){
//        repository.save(new Medico(dados));
//    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid  DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

//    @GetMapping
//    public List<DadosListagemMedico> listar(){
//        return repository.findAll().stream()
//                .map(DadosListagemMedico::new).toList();
//    }

//    @GetMapping
//    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
//        return repository.findAll(paginacao).map(DadosListagemMedico::new);
//    }

    //trazer so méditos ativos
    @GetMapping
    public ResponseEntity <Page<DadosListagemMedico> >listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page =  repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return  ResponseEntity.ok(page);
    }

//    @GetMapping
//    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
//        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
//    }

    @PutMapping
    @Transactional
    //public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
//    Mapeamento para deletar
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id){
//        repository.deleteById(id);
//    }

    //Ativar ou desativar medico
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id){
//        var medico = repository.getReferenceById(id);
//        medico.excluir();
//    }

    //Ativar ou desativar medico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    //Detalhamento de cadastro
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
