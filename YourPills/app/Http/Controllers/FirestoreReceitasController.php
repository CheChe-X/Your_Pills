<?php

namespace App\Http\Controllers;
use Google\Cloud\Firestore\FirestoreClient;
use App\Services\FirestoreReceitasService;
use Illuminate\Http\Request;

class FirestoreReceitasController extends Controller
{

    protected $firebase;

    public function __construct(FirestoreReceitasService $firebase)
    {
        $this->firebase = $firebase;
    }

    public function index()
    {
        $collection = $this->firebase->getFirestore()->collection('Receitas');
        $documents = $collection->documents();

        $data = [];
        foreach ($documents as $document) {
            $data[] = $document->data();
        }

        return view('receitas', compact('data'));
    }
}
