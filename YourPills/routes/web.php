<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\FirebaseContasController;
use App\Http\Controllers\PhoneAuthController;

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

Route::get('api/utilizadores', [FirebaseContasController::class, 'index'])->name('firebase.index');

Route::get('/', function () {
    return view('index');
});

Route::get('/login', [PhoneAuthController::class, 'index']);


Route::get('/comprimidos', function () {
    return view('comprimidos');
});

Route::get('/receitas', function () {
    return view('receitas');
});

Route::get('/about', function () {
    return view('about');
});

Route::get('/contact', function () {
    return view('contact');
});
