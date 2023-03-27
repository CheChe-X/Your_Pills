<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\FirebaseController;

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
});

Route::get('/criar_conta', function () {
    return view('criar_conta');
});

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

Route::get('get-firebase-data', [FirebaseController::class, 'index'])->name('firebase.index');
