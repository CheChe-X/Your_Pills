<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\FirebaseContasController;
use App\Http\Controllers\FirebaseUtilizadoresController;
use App\Http\Controllers\ComprimidoCrudController;
use App\Http\Controllers\FirestoreComprimidosController;
use App\Http\Controllers\FirestoreReceitasController;
use GPBMetadata\Google\Firestore\V1Beta1\Firestore;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/


Route::get('/', function () {
    return view('index');
})->name('home');

Route::resource('crudcomprimidos', ComprimidoCrudController::class);
Route::get('/crudcomprimidos/create', [ComprimidoCrudController::class, 'create'])->name('crudcomprimidos.create');

Route::get('/utilizadores', [FirebaseUtilizadoresController::class, 'index'])->name('utilizadores');
Route::get('/comprimidos', [FirestoreComprimidosController::class, 'index'])->name('comprimidos');
Route::get('/receitas', [FirestoreReceitasController::class, 'index'])->name('receitas');


Route::get('/about', function () {
    return view('about');
});

Route::get('/contact', function () {
    return view('contact');
});

Route::middleware([
    'auth:sanctum',
    config('jetstream.auth_session'),
    'verified'
])->group(function () {
    Route::get('/dashboard', function () {
        return view('dashboard');
    })->name('dashboard');
});
