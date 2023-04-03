<?php

namespace App\Http\Controllers;
use App\Services\FirestoreReceitasService;
use App\Models\Receita;
use Illuminate\Http\Request;

class ReceitaCrudController extends Controller
{
    protected $firestore;

    public function __construct(FirestoreReceitasService $firestore){
        $this->firestore = $firestore;
    }
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('receitas.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $receita = new Receita;
        $receita->nome_receita = $request->nome_receita;
        $receita->n_utente = $request->n_utente;
        $receita->forma_farmaceutica = $request->forma_farmaceutica;
        $receita->dosagem = $request->dosagem;
        $receita->data = $request->data;
        
        $data = $receita->toArray();
        $this->firestore->create($data);

        return redirect()->route('receitas');
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        //
    }
}
