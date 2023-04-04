<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Receita extends Model
{
    protected $fillable = [
        'nome_receita',
        'n_utente',
        'forma_farmaceutica',
        'dosagem',
        'data'
    ];
    use HasFactory;
}
