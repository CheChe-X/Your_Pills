<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use Kreait\Firebase;
use Kreait\Firebase\Factory;

class FirebaseUtilizadoresController extends Controller
{
    public function index()
    {
        $firebase = (new Factory)
            ->withServiceAccount(__DIR__.'/yourpills-b2ff6-firebase-adminsdk-fwy9j-0ead8042fd.json')
            ->withDatabaseUri('https://yourpills-b2ff6-default-rtdb.firebaseio.com');
 
        $database = $firebase->createDatabase();
 
        $conta = $database
        ->getReference('Users');
 
        echo '<pre>';
        print_r($conta->getvalue());
        echo '</pre>';
    }
}
