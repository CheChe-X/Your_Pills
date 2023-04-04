<!DOCTYPE html>
<html lang="en">

<head>
  <!-- basic -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- mobile metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <!-- site metas -->
  <title>YourPills</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="author" content="">
  <!-- bootstrap css -->
  <link rel="stylesheet" href="/css/bootstrap.min.css">
  <!-- style css -->
  <link rel="stylesheet" href="/css/style.css">
  <!-- Responsive-->
  <link rel="stylesheet" href="/css/responsive.css">
  <!-- fevicon -->
  <link rel="icon" href="images/fevicon.png" type="image/gif" />
  <!-- Scrollbar Custom CSS -->
  <link rel="stylesheet" href="/css/jquery.mCustomScrollbar.min.css">
  <!-- Tweaks for older IEs-->
  <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
  <!-- owl stylesheets -->
  <link rel="stylesheet" href="/css/owl.carousel.min.css">
  <link rel="stylesheet" href="/css/owl.theme.default.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
</head>

<body>
<!-- resources/views/receitas/create.blade.php -->
<form action="{{ route('crudreceitas.store') }}" method="POST" class="p-4">
    @csrf
    <div class="form-group">
        <label for="nome">Nome da Receita:</label>
        <input type="text" name="nome_receita" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="embalagens">Nº De Utente:</label>
        <input type="text" name="n_utente" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="medicamentos">Forma Farmaceutica:</label>
        <input type="text" name="forma_farmaceutica" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="miligramas">Dosagem:</label>
        <input type="text" name="dosagem" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="data">Data:</label>
        <input type="date" name="data" class="form-control" required>
    </div>
    <button type="submit" class="btn btn-primary">Criar Receita</button>
</form>
