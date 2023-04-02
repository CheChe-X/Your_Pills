<?php

namespace App\Services;

use Google\Cloud\Firestore\FirestoreClient;

class FirestoreReceitasService
{
    protected $db;

    public function __construct()
    {
        $this->db = new FirestoreClient([
            'projectId' => 'yourpills-b2ff6',
        ]);
    }

    public function getFirestore()
    {
        return $this->db;
    }
}
