<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;


use Kreait\Firebase\Factory;
use Kreait\Firebase\ServiceAccount;

class FirebaseComprimidosController extends Controller
{
    public function index()
    {
        $serviceAccount = ServiceAccount::fromJsonFile(__DIR__ . '../../database/yourpills-b2ff6-firebase-adminsdk-fwy9j-6d75acf873.json');
        $firebase = (new Factory)
            ->withServiceAccount($serviceAccount)
            ->create();

        $database = $firebase->getDatabase();

        $comprimidos = $database->getReference('comprimidos')->getValue();

        return view('comprimidos', compact('comprimidos'));
    }
}
