<?php

namespace App\Http\Controllers;
use Google\Cloud\Firestore\FirestoreClient;
use App\Services\FirestoreComprimidosService;
use Illuminate\Http\Request;

class FirestoreComprimidosController extends Controller
{

    protected $firebase;

    public function __construct(FirestoreComprimidosService $firebase)
    {
        $this->firebase = $firebase;
    }

    public function index()
    {
        $collection = $this->firebase->getFirestore()->collection('Comprimidos');
        $documents = $collection->documents();

        $data = [];
        foreach ($documents as $document) {
            $data[] = $document->data();
        }

        return view('comprimidos', compact('data'));
    }
}
