<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('comprimidos', function (Blueprint $table) {
            $table->id();
            $table->string('nome');
            $table->string('embalagens');
            $table->string('medicamentos');
            $table->string('miligramas');
            $table->string('data');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('comprimidos');
    }
};
