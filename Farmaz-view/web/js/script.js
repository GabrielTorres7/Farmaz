/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Excluir(chave, frm) {
    var table = frm.table.value;

    if (table == "MeuCarrinho") {
        frm.acao.value = "ExcluirProdutoCarrinho";
        frm.cod.value = chave;
        frm.submit();
    }
}

function SetAcao(acao, frm) {    
    frm.acao.value = acao;
    frm.submit();
}

function SetAcaoPedido(acao, frm, id, status) {
    frm.cod.value = id;
    frm.acao.value = acao;
    frm.status.value = status;
    frm.submit();
}
