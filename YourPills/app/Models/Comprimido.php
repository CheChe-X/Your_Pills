<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Comprimido extends Model
{
    protected $fillable = [
        'nome',
        'embalagens',
        'medicamentos',
        'miligramas',
        'data'
    ];
    
    use HasFactory;
}
