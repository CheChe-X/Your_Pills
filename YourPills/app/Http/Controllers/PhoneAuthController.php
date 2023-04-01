<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class PhoneAuthController extends Controller
{
  public function index() {
    if (session('phone_authenticated')) {
        $phone_authenticated = true;
    } else {
        $phone_authenticated = false;
    }

    return view('index', [
        'phone_authenticated' => $phone_authenticated
    ]);
}
}
